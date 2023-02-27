package com.example.ventas.services.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ventas.models.transactions.Transaction;
import com.example.ventas.models.transactions.TransactionsRepository;
import com.example.ventas.utils.CustomResponse;

import java.util.List;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionsRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Transaction>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Transaction> getOne(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La transacción no existe"
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
    public CustomResponse<Transaction> insert(Transaction transaction){
        return new CustomResponse<>(
                this.repository.saveAndFlush(transaction),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Transaction> update(Transaction transaction){
        if(!this.repository.existsById(transaction.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La transacción no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.saveAndFlush(transaction),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Transaction> delete(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La transacción no existe"
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
}
