/**
 * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Copyright (C) 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import models.UserEntity;
import ninja.jpa.UnitOfWork;

//import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import controllers.LoginLogoutController;


public class UserDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    private static Logger log = LogManager.getLogger(UserDao.class);
    @Transactional
    public boolean isUserAndPasswordValid(String username, String password) {
        
        if (username != null && password != null) {
            log.info(password);
            log.info(username);
            EntityManager entityManager = entityManagerProvider.get();
            
			TypedQuery<UserEntity> q = entityManager.createQuery("SELECT x FROM UserEntity x WHERE username = :usernameParam", UserEntity.class);
            UserEntity user = getSingleResult(q.setParameter("usernameParam", username));
            log.info(user);

            if (user != null) {
                
                if (user.password.equals(password)) {

                    return true;
                    
                }
                
                
            }

        }
        
        return false;
 
    }

    private static <T> T getSingleResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

}
