package com.example.warThunder.repository.impl;

import com.example.warThunder.model.Field;
import com.example.warThunder.repository.FieldDao;
import org.springframework.stereotype.Repository;

@Repository
public class FieldDaoImpl extends AbstractDao<Field> implements FieldDao {

    @Override
    protected Class<Field> getEntityClass() {
        return Field.class;
    }
}
