package com.example.repository.impl;

import com.example.models.Field;
import com.example.repository.FieldDao;
import org.springframework.stereotype.Repository;

@Repository
public class FieldDaoImpl extends AbstractDao<Field> implements FieldDao {

    @Override
    protected Class<Field> getEntityClass() {
        return Field.class;
    }
}
