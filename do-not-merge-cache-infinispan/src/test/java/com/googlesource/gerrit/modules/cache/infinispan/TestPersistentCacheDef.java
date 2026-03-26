// Copyright (C) 2020 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.googlesource.gerrit.modules.cache.infinispan;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.Weigher;
import com.google.gerrit.server.cache.PersistentCacheDef;
import com.google.gerrit.server.cache.serialize.CacheSerializer;
import com.google.gerrit.server.cache.serialize.StringCacheSerializer;
import com.google.inject.TypeLiteral;
import java.time.Duration;
import java.util.UUID;

public class TestPersistentCacheDef implements PersistentCacheDef<String, String> {

  private static final Duration ONE_DAY = Duration.ofDays(1);
  private static final Integer DEFAULT_MEMORY_LIMIT = 1024;

  private final String name;
  private final Duration expireAfterWrite;
  private final Duration refreshAfterWrite;
  private final Duration expireFromMemoryAfterAccess;
  private final Integer maximumWeight;
  private final Integer diskLimit;
  private final CacheSerializer<String> keySerializer;
  private final CacheSerializer<String> valueSerializer;

  public TestPersistentCacheDef(String name, Integer diskLimit) {
    this.name = name;
    this.expireAfterWrite = ONE_DAY;
    this.refreshAfterWrite = null;
    this.expireFromMemoryAfterAccess = ONE_DAY;
    this.diskLimit = diskLimit;
    this.keySerializer = StringCacheSerializer.INSTANCE;
    this.valueSerializer = StringCacheSerializer.INSTANCE;
    this.maximumWeight = DEFAULT_MEMORY_LIMIT;
  }

  @Override
  public long diskLimit() {
    return diskLimit;
  }

  @Override
  public int version() {
    return 0;
  }

  @Override
  public CacheSerializer<String> keySerializer() {
    return keySerializer;
  }

  @Override
  public CacheSerializer<String> valueSerializer() {
    return valueSerializer;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String configKey() {
    return name();
  }

  @Override
  public TypeLiteral<String> keyType() {
    return new TypeLiteral<>() {};
  }

  @Override
  public TypeLiteral<String> valueType() {
    return new TypeLiteral<>() {};
  }

  @Override
  public long maximumWeight() {
    return maximumWeight;
  }

  @Override
  public Duration expireAfterWrite() {
    return expireAfterWrite;
  }

  @Override
  public Duration expireFromMemoryAfterAccess() {
    return expireFromMemoryAfterAccess;
  }

  @Override
  public Duration refreshAfterWrite() {
    return refreshAfterWrite;
  }

  @Override
  public Weigher<String, String> weigher() {
    return (s, s2) -> 0;
  }

  @Override
  public CacheLoader<String, String> loader() {
    return new CacheLoader<>() {
      @Override
      public String load(String s) {
        return UUID.randomUUID().toString();
      }
    };
  }
}
