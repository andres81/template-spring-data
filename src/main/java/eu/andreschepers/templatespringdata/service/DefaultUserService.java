/*
 * Copyright 2016 Andre Schepers
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
package eu.andreschepers.templatespringdata.service;

import eu.andreschepers.templatespringdata.domain.UserEntity;
import eu.andreschepers.templatespringdata.repositories.UserRepository;
import eu.andreschepers.templatespringdata.service.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultUserService implements UserService {

    @Resource
    private UserRepository userRepo;

    @Transactional
    public UserEntity add(UserDTO added) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(added.getName());
        userEntity.setPassword(added.getPassword());
        userEntity.setUserID(added.getUserID());

        return userRepo.save(userEntity);
    }

    @Transactional
    public List<UserEntity> findAll() {
        return userRepo.findAll();
    }

    @Transactional
    public UserEntity update(UserDTO updated) {
        UserEntity userEntity = userRepo.findByUserID(updated.getUserID());
        userEntity.setName(updated.getName());
        userEntity.setPassword(updated.getPassword());

        return userEntity;
    }
}