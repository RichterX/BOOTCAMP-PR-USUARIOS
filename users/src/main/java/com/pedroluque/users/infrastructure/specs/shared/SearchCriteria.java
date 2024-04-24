package com.pedroluque.users.infrastructure.specs.shared;

public class SearchCriteria
{
    private String key;
    private Object value;
    private SearchOperation operation;

    public SearchCriteria()
    {
        //Constructor vacio
    }

        // Nuevo constructor que acepta key, value y operation para el posterior filtrado
    public SearchCriteria(String key, Object value, SearchOperation operation)
    {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }


    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public SearchOperation getOperation()
    {
        return operation;
    }

    public void setOperation(SearchOperation operation)
    {
        this.operation = operation;
    }
}
