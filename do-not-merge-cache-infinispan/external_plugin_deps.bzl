load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  maven_jar(
      name = "infinispan-core",
      artifact = "org.infinispan:infinispan-core:15.2.6.Final",
  )

  maven_jar(
      name = "caffeine",
      artifact = "com.github.ben-manes.caffeine:caffeine:3.2.0",
  )

  maven_jar(
      name = "error_prone_annotations",
      artifact = "com.google.errorprone:error_prone_annotations:2.36.0",
  )

  maven_jar(
      name = "jspecify",
      artifact = "org.jspecify:jspecify:1.0.0",
  )

  maven_jar(
      name = "jakarta-transaction-api",
      artifact = "jakarta.transaction:jakarta.transaction-api:2.0.1",
  )

  maven_jar(
      name = "infinispan-commons",
      artifact = "org.infinispan:infinispan-commons:15.2.6.Final",
  )

  maven_jar(
      name = "rxjava",
      artifact = "io.reactivex.rxjava3:rxjava:3.1.10",
  )

  maven_jar(
      name = "reactive-streams",
      artifact = "org.reactivestreams:reactive-streams:1.0.4",
  )

  maven_jar(
      name = "infinispan-logging-processor",
      artifact = "org.infinispan:infinispan-logging-processor:15.2.6.Final",
  )

  maven_jar(
      name = "infinispan-logging-annotations",
      artifact = "org.infinispan:infinispan-logging-annotations:15.2.6.Final",
  )

  maven_jar(
      name = "jboss-logging",
      artifact = "org.jboss.logging:jboss-logging:3.6.1.Final",
  )

  maven_jar(
      name = "jboss-logging-annotations",
      artifact = "org.jboss.logging:jboss-logging-annotations:3.0.4.Final",
  )

  maven_jar(
      name = "metainf-services",
      artifact = "org.kohsuke.metainf-services:metainf-services:1.11",
  )

  maven_jar(
      name = "protostream",
      artifact = "org.infinispan.protostream:protostream:5.0.13.Final",
  )

  maven_jar(
      name = "jackson-core",
      artifact = "com.fasterxml.jackson.core:jackson-core:2.18.1",
  )

  maven_jar(
      name = "protostream-types",
      artifact = "org.infinispan.protostream:protostream-types:5.0.13.Final",
  )

  maven_jar(
      name = "protostream-processor",
      artifact = "org.infinispan.protostream:protostream-processor:5.0.13.Final",
  )

  maven_jar(
      name = "infinispan-commons-spi",
      artifact = "org.infinispan:infinispan-commons-spi:15.2.6.Final",
  )

  maven_jar(
      name = "infinispan-component-processor",
      artifact = "org.infinispan:infinispan-component-processor:15.2.6.Final",
  )

  maven_jar(
      name = "infinispan-component-annotations",
      artifact = "org.infinispan:infinispan-component-annotations:15.2.6.Final",
  )

  maven_jar(
      name = "jgroups",
      artifact = "org.jgroups:jgroups:5.4.5.Final",
  )

  maven_jar(
      name = "infinispan-counter-api",
      artifact = "org.infinispan:infinispan-counter-api:15.2.6.Final",
  )

  maven_jar(
      name = "jboss-threads",
      artifact = "org.jboss.threads:jboss-threads:3.6.1.Final",
  )

  maven_jar(
      name = "wildfly-common",
      artifact = "org.wildfly.common:wildfly-common:1.7.0.Final",
  )

