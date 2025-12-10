# Sample usage:
#
#   $env:TEST_HOST="localhost"; $env:PORT=7000; .\test-em-all-windows.ps1
#   .\test-em-all-windows.ps1 start
#   .\test-em-all-windows.ps1 stop
#   .\test-em-all-windows.ps1 start stop
#

param(
    [string[]]$args
)

# Default values
$TEST_HOST = if ($env:TEST_HOST) { $env:TEST_HOST } else { "localhost" }
$TEST_PORT = if ($env:PORT) { $env:PORT } else { "8080" }
$PROD_ID_REVS_RECS = if ($env:PROD_ID_REVS_RECS) { $env:PROD_ID_REVS_RECS } else { "1" }
$PROD_ID_NOT_FOUND = if ($env:PROD_ID_NOT_FOUND) { $env:PROD_ID_NOT_FOUND } else { "13" }
$PROD_ID_NO_RECS = if ($env:PROD_ID_NO_RECS) { $env:PROD_ID_NO_RECS } else { "113" }
$PROD_ID_NO_REVS = if ($env:PROD_ID_NO_REVS) { $env:PROD_ID_NO_REVS } else { "213" }

$script:RESPONSE = ""

function Assert-Curl {
    param(
        [int]$expectedHttpCode,
        [string]$curlCmd
    )
    
    try {
        $result = Invoke-Expression "$curlCmd -w '%{http_code}'"
        $httpCode = $result.Substring($result.Length - 3)
        $script:RESPONSE = if ($result.Length -gt 3) { $result.Substring(0, $result.Length - 3) } else { "" }
        
        if ($httpCode -eq $expectedHttpCode.ToString()) {
            if ($httpCode -eq "200") {
                Write-Host "Test OK (HTTP Code: $httpCode)" -ForegroundColor Green
            } else {
                Write-Host "Test OK (HTTP Code: $httpCode, $script:RESPONSE)" -ForegroundColor Green
            }
        } else {
            Write-Host "Test FAILED, EXPECTED HTTP Code: $expectedHttpCode, GOT: $httpCode, WILL ABORT!" -ForegroundColor Red
            Write-Host "- Failing command: $curlCmd" -ForegroundColor Red
            Write-Host "- Response Body: $script:RESPONSE" -ForegroundColor Red
            exit 1
        }
    } catch {
        Write-Host "Test FAILED with exception: $_" -ForegroundColor Red
        exit 1
    }
}

function Assert-Equal {
    param(
        [string]$expected,
        [string]$actual
    )
    
    if ($actual -eq $expected) {
        Write-Host "Test OK (actual value: $actual)" -ForegroundColor Green
    } else {
        Write-Host "Test FAILED, EXPECTED VALUE: $expected, ACTUAL VALUE: $actual, WILL ABORT" -ForegroundColor Red
        exit 1
    }
}

function Test-Url {
    param([string]$url)
    
    try {
        $response = Invoke-Expression "curl -ks -f -o `$null -w '%{http_code}' $url"
        return $response -eq "200"
    } catch {
        return $false
    }
}

function Wait-ForService {http://localhost:8080/
    param([string]$url)
    
    Write-Host -NoNewline "Wait for: $url... "
    $n = 0
    
    while (-not (Test-Url $url)) {
        $n++
        if ($n -eq 100) {
            Write-Host " Give up" -ForegroundColor Red
            exit 1
        } else {
            Start-Sleep -Seconds 3
            Write-Host -NoNewline ", retry #$n "
        }
    }
    
    Write-Host "DONE, continues..." -ForegroundColor Green
}

Write-Host "`nStart Tests: $(Get-Date)" -ForegroundColor Cyan
Write-Host "HOST=$TEST_HOST"
Write-Host "PORT=$TEST_PORT"

# Check if 'start' argument is passed
if ($args -contains "start") {
    Write-Host "`nRestarting the test environment..." -ForegroundColor Yellow
    Write-Host "$ docker compose down --remove-orphans"
    docker compose down --remove-orphans
    Write-Host "$ docker compose up -d"
    docker compose up -d
}

Wait-ForService "curl http://$TEST_HOST`:$TEST_PORT/product-composite/$PROD_ID_REVS_RECS"

# Verify that a normal request works, expect three recommendations and three reviews
Write-Host "`nTest 1: Normal request with recommendations and reviews" -ForegroundColor Cyan
Assert-Curl 200 "curl http://$TEST_HOST`:$TEST_PORT/product-composite/$PROD_ID_REVS_RECS -s"
$productId = (echo $script:RESPONSE | jq .productId)
Assert-Equal $PROD_ID_REVS_RECS $productId
$recsLength = (echo $script:RESPONSE | jq ".recommendations | length")
Assert-Equal "3" $recsLength
$revsLength = (echo $script:RESPONSE | jq ".reviews | length")
Assert-Equal "3" $revsLength

# Verify that a 404 (Not Found) error is returned for a non-existing productId
Write-Host "`nTest 2: 404 Not Found for non-existing product" -ForegroundColor Cyan
Assert-Curl 404 "curl http://$TEST_HOST`:$TEST_PORT/product-composite/$PROD_ID_NOT_FOUND -s"
$message = (echo $script:RESPONSE | jq -r .message)
Assert-Equal "No product found for productId: $PROD_ID_NOT_FOUND" $message

# Verify that no recommendations are returned for productId $PROD_ID_NO_RECS
Write-Host "`nTest 3: Product with no recommendations" -ForegroundColor Cyan
Assert-Curl 200 "curl http://$TEST_HOST`:$TEST_PORT/product-composite/$PROD_ID_NO_RECS -s"
$productId = (echo $script:RESPONSE | jq .productId)
Assert-Equal $PROD_ID_NO_RECS $productId
$recsLength = (echo $script:RESPONSE | jq ".recommendations | length")
Assert-Equal "0" $recsLength
$revsLength = (echo $script:RESPONSE | jq ".reviews | length")
Assert-Equal "3" $revsLength

# Verify that no reviews are returned for productId $PROD_ID_NO_REVS
Write-Host "`nTest 4: Product with no reviews" -ForegroundColor Cyan
Assert-Curl 200 "curl http://$TEST_HOST`:$TEST_PORT/product-composite/$PROD_ID_NO_REVS -s"
$productId = (echo $script:RESPONSE | jq .productId)
Assert-Equal $PROD_ID_NO_REVS $productId
$recsLength = (echo $script:RESPONSE | jq ".recommendations | length")
Assert-Equal "3" $recsLength
$revsLength = (echo $script:RESPONSE | jq ".reviews | length")
Assert-Equal "0" $revsLength

# Verify that a 422 (Unprocessable Entity) error is returned for a productId that is out of range (-1)
Write-Host "`nTest 5: 422 Unprocessable Entity for invalid productId (-1)" -ForegroundColor Cyan
Assert-Curl 422 "curl http://$TEST_HOST`:$TEST_PORT/product-composite/-1 -s"
$message = (echo $script:RESPONSE | jq .message)
Assert-Equal "`"Invalid productId: -1`"" $message

# Verify that a 400 (Bad Request) error is returned for a productId that is not a number
Write-Host "`nTest 6: 400 Bad Request for non-numeric productId" -ForegroundColor Cyan
Assert-Curl 400 "curl http://$TEST_HOST`:$TEST_PORT/product-composite/invalidProductId -s"
$message = (echo $script:RESPONSE | jq .message)
Assert-Equal "`"Type mismatch.`"" $message

# Verify access to Swagger and OpenAPI URLs
Write-Host "`nSwagger/OpenAPI tests" -ForegroundColor Cyan
Assert-Curl 302 "curl -s http://$TEST_HOST`:$TEST_PORT/openapi/swagger-ui.html"
Assert-Curl 200 "curl -sL http://$TEST_HOST`:$TEST_PORT/openapi/swagger-ui.html"
Assert-Curl 200 "curl -s http://$TEST_HOST`:$TEST_PORT/openapi/webjars/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config"
Assert-Curl 200 "curl -s http://$TEST_HOST`:$TEST_PORT/openapi/v3/api-docs"
$openapi = (echo $script:RESPONSE | jq -r .openapi)
Assert-Equal "3.0.1" $openapi
$serverUrl = (echo $script:RESPONSE | jq -r '.servers[0].url')
Assert-Equal "http://$TEST_HOST`:$TEST_PORT" $serverUrl
Assert-Curl 200 "curl -s http://$TEST_HOST`:$TEST_PORT/openapi/v3/api-docs.yaml"

# Check if 'stop' argument is passed
if ($args -contains "stop") {
    Write-Host "`nWe are done, stopping the test environment..." -ForegroundColor Yellow
    Write-Host "$ docker compose down"
    docker compose down
}

Write-Host "`nEnd, all tests OK: $(Get-Date)" -ForegroundColor Green
