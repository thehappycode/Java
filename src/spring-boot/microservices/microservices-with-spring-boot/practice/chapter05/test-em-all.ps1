#
# Sample usage:
#
#   $env:HOST="localhost"; $env:PORT=7000; .\test-em-all-v2.ps1
#   Or simply: .\test-em-all-v2.ps1
#

# Set default values if environment variables are not set
$HOST = if ($env:HOST) { $env:HOST } else { "localhost" }
$PORT = if ($env:PORT) { $env:PORT } else { 7000 }
$PROD_ID_REVS_RECS = if ($env:PROD_ID_REVS_RECS) { $env:PROD_ID_REVS_RECS } else { 1 }
$PROD_ID_NOT_FOUND = if ($env:PROD_ID_NOT_FOUND) { $env:PROD_ID_NOT_FOUND } else { 13 }
$PROD_ID_NO_RECS = if ($env:PROD_ID_NO_RECS) { $env:PROD_ID_NO_RECS } else { 113 }
$PROD_ID_NO_REVS = if ($env:PROD_ID_NO_REVS) { $env:PROD_ID_NO_REVS } else { 213 }

# Global variable to store response body
$script:RESPONSE = ""

function Assert-Curl {
    param(
        [int]$ExpectedHttpCode,
        [string]$Url
    )

    try {
        $response = Invoke-WebRequest -Uri $Url -UseBasicParsing -ErrorAction SilentlyContinue
        $httpCode = [int]$response.StatusCode
        $script:RESPONSE = $response.Content
    }
    catch {
        if ($_.Exception.Response) {
            $httpCode = [int]$_.Exception.Response.StatusCode
            $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
            $script:RESPONSE = $reader.ReadToEnd()
            $reader.Close()
        }
        else {
            Write-Host "Test FAILED, Unable to connect to $Url" -ForegroundColor Red
            Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
            exit 1
        }
    }

    if ($httpCode -eq $ExpectedHttpCode) {
        if ($httpCode -eq 200) {
            Write-Host "Test OK (HTTP Code: $httpCode)" -ForegroundColor Green
        }
        else {
            Write-Host "Test OK (HTTP Code: $httpCode, $script:RESPONSE)" -ForegroundColor Green
        }
    }
    else {
        Write-Host "Test FAILED, EXPECTED HTTP Code: $ExpectedHttpCode, GOT: $httpCode, WILL ABORT!" -ForegroundColor Red
        Write-Host "- Failing URL: $Url" -ForegroundColor Red
        Write-Host "- Response Body: $script:RESPONSE" -ForegroundColor Red
        exit 1
    }
}

function Assert-Equal {
    param(
        $Expected,
        $Actual
    )

    if ($Actual -eq $Expected) {
        Write-Host "Test OK (actual value: $Actual)" -ForegroundColor Green
    }
    else {
        Write-Host "Test FAILED, EXPECTED VALUE: $Expected, ACTUAL VALUE: $Actual, WILL ABORT" -ForegroundColor Red
        exit 1
    }
}

# Stop on errors
$ErrorActionPreference = "Stop"

Write-Host "HOST=$HOST"
Write-Host "PORT=$PORT"
Write-Host ""

# Verify that a normal request works, expect three recommendations and three reviews
Write-Host "Test 1: Normal request with recommendations and reviews"
Assert-Curl -ExpectedHttpCode 200 -Url "http://${HOST}:${PORT}/product-composite/$PROD_ID_REVS_RECS"
$json = $script:RESPONSE | ConvertFrom-Json
Assert-Equal -Expected $PROD_ID_REVS_RECS -Actual $json.productId
Assert-Equal -Expected 3 -Actual $json.recommendations.Count
Assert-Equal -Expected 3 -Actual $json.reviews.Count

# Verify that a 404 (Not Found) error is returned for a non-existing productId
Write-Host "`nTest 2: 404 error for non-existing product"
Assert-Curl -ExpectedHttpCode 404 -Url "http://${HOST}:${PORT}/product-composite/$PROD_ID_NOT_FOUND"
$json = $script:RESPONSE | ConvertFrom-Json
Assert-Equal -Expected "No product found for productId: $PROD_ID_NOT_FOUND" -Actual $json.message

# Verify that no recommendations are returned for productId $PROD_ID_NO_RECS
Write-Host "`nTest 3: Product with no recommendations"
Assert-Curl -ExpectedHttpCode 200 -Url "http://${HOST}:${PORT}/product-composite/$PROD_ID_NO_RECS"
$json = $script:RESPONSE | ConvertFrom-Json
Assert-Equal -Expected $PROD_ID_NO_RECS -Actual $json.productId
Assert-Equal -Expected 0 -Actual $json.recommendations.Count
Assert-Equal -Expected 3 -Actual $json.reviews.Count

# Verify that no reviews are returned for productId $PROD_ID_NO_REVS
Write-Host "`nTest 4: Product with no reviews"
Assert-Curl -ExpectedHttpCode 200 -Url "http://${HOST}:${PORT}/product-composite/$PROD_ID_NO_REVS"
$json = $script:RESPONSE | ConvertFrom-Json
Assert-Equal -Expected $PROD_ID_NO_REVS -Actual $json.productId
Assert-Equal -Expected 3 -Actual $json.recommendations.Count
Assert-Equal -Expected 0 -Actual $json.reviews.Count

# Verify that a 422 (Unprocessable Entity) error is returned for a productId that is out of range (-1)
Write-Host "`nTest 5: 422 error for invalid productId (-1)"
Assert-Curl -ExpectedHttpCode 422 -Url "http://${HOST}:${PORT}/product-composite/-1"
$json = $script:RESPONSE | ConvertFrom-Json
Assert-Equal -Expected "Invalid productId: -1" -Actual $json.message

# Verify that a 400 (Bad Request) error is returned for a productId that is not a number
Write-Host "`nTest 6: 400 error for invalid productId format"
Assert-Curl -ExpectedHttpCode 400 -Url "http://${HOST}:${PORT}/product-composite/invalidProductId"
$json = $script:RESPONSE | ConvertFrom-Json
Assert-Equal -Expected "Type mismatch." -Actual $json.message

Write-Host "`nEnd, all tests OK: $(Get-Date)" -ForegroundColor Cyan
