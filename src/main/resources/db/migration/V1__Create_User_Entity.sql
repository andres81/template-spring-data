-- Copyright 2016 Andre Schepers
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.

CREATE TABLE user_entity (
    id BIGINT UNSIGNED auto_increment,
    version BIGINT UNSIGNED,
    name VARCHAR(255), last_name VARCHAR(255),
    PRIMARY KEY (id)
);