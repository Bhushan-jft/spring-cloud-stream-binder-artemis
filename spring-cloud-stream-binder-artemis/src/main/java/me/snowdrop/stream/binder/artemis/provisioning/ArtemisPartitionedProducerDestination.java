/*
 * Copyright 2016-2017 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.snowdrop.stream.binder.artemis.provisioning;

import org.springframework.cloud.stream.provisioning.ProducerDestination;

import java.util.List;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
public class ArtemisPartitionedProducerDestination implements ProducerDestination {

    private final List<String> names;

    public ArtemisPartitionedProducerDestination(List<String> names) {
        this.names = names;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("This destination is partitioned");
    }

    @Override
    public String getNameForPartition(int partition) {
        if (partition >= names.size() || partition < 0) {
            throw new IllegalStateException(String.format("Partition '%d' doesn't exist", partition));
        }

        return names.get(partition);
    }

    @Override
    public String toString() {
        return String.format("%s{names=%s}", ArtemisPartitionedProducerDestination.class.getSimpleName(), names);
    }

}
