package com.googlesource.gerrit.modules.cache.infinispan;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.cache.Cache;
import com.google.gerrit.acceptance.AbstractDaemonTest;
import com.google.gerrit.acceptance.UseLocalDisk;
import com.google.gerrit.entities.AccountGroup;
import com.google.gerrit.entities.Project;
import com.google.gerrit.extensions.api.accounts.AccountInput;
import com.google.gerrit.extensions.api.groups.GroupInput;
import com.google.gerrit.extensions.api.projects.ProjectInput;
import com.google.gerrit.extensions.common.AccountInfo;
import com.google.gerrit.extensions.common.GroupInfo;
import com.google.gerrit.extensions.common.ProjectInfo;
import com.google.gerrit.server.cache.PersistentCacheFactory;
import com.google.inject.Inject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

@UseLocalDisk
public class InfinispanCacheIT extends AbstractDaemonTest {
  @Rule public TestName testName = new TestName();

  @Inject PersistentCacheFactory persistentCacheFactory;

  @Override
  public com.google.inject.Module createModule() {
    return new InfinispanCacheModule();
  }

  @Test
  public void shouldBeAbleToInstallCacheFactory() {
    assertThat(persistentCacheFactory).isInstanceOf(InfinispanCacheFactory.class);
  }

  @Test
  public void shouldBuildInMemoryCacheWhenDiskLimitIsZero() {
    final Cache<String, String> cache =
        persistentCacheFactory.build(new TestPersistentCacheDef(testName.getMethodName(), 0));
    assertThat(cache.getClass().getSimpleName()).isEqualTo("CaffeinatedGuavaCache");
  }

  @Test
  public void shouldBuildInfinispanCacheWhenDiskLimitIsPositive() {
    final int positiveDiskLimit = 128 << 20; // 128 MiB
    assertThat(
            persistentCacheFactory.build(
                new TestPersistentCacheDef(testName.getMethodName(), positiveDiskLimit)))
        .isInstanceOf(InfinispanCacheImpl.class);
  }

  @Test
  public void shouldCacheNewProject() throws Exception {
    ProjectInput input = new ProjectInput();
    input.name = "foo";

    assertThat(projectCache.get(Project.nameKey(input.name))).isEmpty();
    ProjectInfo ignored = gApi.projects().create(input).get();
    assertThat(projectCache.get(Project.nameKey(input.name))).isPresent();
  }

  @Test
  public void shouldCacheNewUser() throws Exception {
    AccountInput input = new AccountInput();
    input.username = "foo";

    assertThat(accountCache.getByUsername(input.username)).isEmpty();
    AccountInfo ignored = gApi.accounts().create(input).get();
    assertThat(accountCache.getByUsername(input.username)).isPresent();
  }

  @Test
  public void shouldCacheNewGroup() throws Exception {
    GroupInput input = new GroupInput();
    input.name = "foo";

    assertThat(groupCache.get(AccountGroup.nameKey(input.name))).isEmpty();
    GroupInfo ignored = gApi.groups().create(input).get();
    assertThat(groupCache.get(AccountGroup.nameKey(input.name))).isPresent();
  }
}
