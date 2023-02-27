package com.example.ventas.services.products;

import com.example.ventas.models.products.Product;
import com.example.ventas.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ventas.models.products.ProductRepository;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Product>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Product> getOne(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El producto no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Product> insert(Product product){
        return new CustomResponse<>(
                this.repository.saveAndFlush(product),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Product> update(Product product){
        if(!this.repository.existsById(product.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El producto no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.saveAndFlush(product),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Product> delete(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El producto no existe"
            );
        }

        this.repository.deleteById(id);

        return new CustomResponse<>(
                null,
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Integer> changeStatus(Product product){
        if(!this.repository.existsById(product.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El producto no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.updateStatusById(product.getStatus(), product.getId()),
                false,
                200,
                "Ok"
        );
    }
}
