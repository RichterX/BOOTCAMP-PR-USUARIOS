package com.pedroluque.users.infrastructure.specs;

import com.pedroluque.users.domain.entity.User;
import com.pedroluque.users.infrastructure.specs.shared.SearchCriteria;
import com.pedroluque.users.infrastructure.specs.shared.EntitySpecificacion;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class UserSpecification extends EntitySpecificacion<User> implements Specification<User>
{
    public UserSpecification(List<SearchCriteria> criteria)
    {
        this.criteria = criteria;
    }

}
