package com.thehappycode.inventory.service;


public interface InventoryService {

    /*
     * Kiểm tra xem trong inventory có tồn tại dòng hàng có skuCode, quantity lớn hơn tham số truyền vào không?
     * @param skuCode
     * @param quantity
     * @return
    */
    boolean isInStock(String skuCode, Integer quantity);
}


