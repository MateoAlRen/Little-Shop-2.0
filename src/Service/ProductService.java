package Service;

import Models.CrudFunctions;


public class ProductService extends CrudFunctions {
    public ProductService(){
        setContainer();
        addProduct();
        readProduct();
        search();
        buyProducts();
        Statistics();
        Exit();
    }
}
