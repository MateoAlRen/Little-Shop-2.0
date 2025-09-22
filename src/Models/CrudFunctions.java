package Models;

import Interfaces.ProductsCrud;

import javax.swing.*;

public abstract class CrudFunctions implements ProductsCrud {
    protected JFrame container;

    public void setContainer(){
        container = Utils.Container.createWindow();
    }
    @Override
    public void addProduct(){
        Utils.Create.button(container);
    }


    @Override
    public void readProduct() {
                Utils.Read.Button(container);
    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void search() {
        Utils.Search.Button(container);
    }

    @Override
    public void buyProducts(){
        Utils.Buy.Button(container);
    }

    public void Statistics(){
        Utils.Statistics.Button(container);
    }

    public void Exit(){
        Utils.Exit.Button(container);
    }
}
