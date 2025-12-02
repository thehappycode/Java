package thehappycode.microservices.core.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import thehappycode.api.core.product.ProductService;
import thehappycode.api.core.product.Product;
import thehappycode.util.http.ServiceUtil;

@RestController
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ServiceUtil serviceUtil;

    public ProductServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Product getProduct(int productId) {
        return new Product(productId,
                "name-" + productId,
                123,
                serviceUtil.getServiceAddress());
    }
}
