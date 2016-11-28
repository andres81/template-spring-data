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
package eu.andreschepers.templatespringdata;

import eu.andreschepers.templatespringdata.service.UserService;
import eu.andreschepers.templatespringdata.service.dto.UserDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext("eu.andreschepers.templatespringdata.configuration");

        UserDTO dto = new UserDTO();
        dto.setName("Andre");
        dto.setPassword("password");
        dto.setUserID(UUID.randomUUID());

        UserService userService = ctx.getBean(UserService.class);
        userService.add(dto);
    }
}