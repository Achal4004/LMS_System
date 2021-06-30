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

package dao;

import java.util.List;

import com.google.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.Article;

import models.UserEntity;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import javax.persistence.FlushModeType;

public class SetupDao {

	@Inject
	Provider<EntityManager> entityManagerProvider;

	@Transactional
	public void setup() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<UserEntity> q = entityManager.createQuery("SELECT x FROM UserEntity x", UserEntity.class);
		List<UserEntity> users = q.getResultList();

		if (users.size() == 0) {

			// Create a new user and save it
			UserEntity bob = new UserEntity("bob@gmail.com", "secret", "Bob");
			entityManager.persist(bob);
			entityManager.setFlushMode(FlushModeType.COMMIT);
			entityManager.flush();
		}

	}

}
