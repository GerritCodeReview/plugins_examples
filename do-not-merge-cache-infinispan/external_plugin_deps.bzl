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

  maven_jar(
      name = "infinispan-cachestore-remote",
      artifact = "org.infinispan:infinispan-cachestore-remote:15.2.6.Final",
  )

  maven_jar(
      name = "infinispan-multimap",
      artifact = "org.infinispan:infinispan-multimap:15.2.6.Final",
  )

  maven_jar(
      name = "infinispan-client-hotrod",
      artifact = "org.infinispan:infinispan-client-hotrod:15.2.6.Final",
  )

  maven_jar(
      name = "netty-handler",
      artifact = "io.netty:netty-handler:4.1.124.Final",
  )

  maven_jar(
      name = "gson",
      artifact = "com.google.code.gson:gson:2.8.9",
  )

  maven_jar(
      name = "netty-common",
      artifact = "io.netty:netty-common:4.1.124.Final",
  )

  maven_jar(
      name = "netty-resolver",
      artifact = "io.netty:netty-resolver:4.1.124.Final",
  )

  maven_jar(
      name = "mockito-core",
      artifact = "org.mockito:mockito-core:2.18.3",
  )

  maven_jar(
      name = "byte-buddy",
      artifact = "net.bytebuddy:byte-buddy:1.8.5",
  )

  maven_jar(
      name = "byte-buddy-agent",
      artifact = "net.bytebuddy:byte-buddy-agent:1.8.5",
  )

  maven_jar(
      name = "objenesis",
      artifact = "org.objenesis:objenesis:2.6",
  )

  maven_jar(
      name = "netty-buffer",
      artifact = "io.netty:netty-buffer:4.1.124.Final",
  )

  maven_jar(
      name = "netty-transport",
      artifact = "io.netty:netty-transport:4.1.124.Final",
  )

  maven_jar(
      name = "reflections",
      artifact = "org.reflections:reflections:0.10.2",
  )

  maven_jar(
      name = "jsr305",
      artifact = "com.google.code.findbugs:jsr305:3.0.2",
  )

  maven_jar(
      name = "javassist",
      artifact = "org.javassist:javassist:3.28.0-GA",
  )

  maven_jar(
      name = "slf4j-api",
      artifact = "org.slf4j:slf4j-api:1.7.32",
  )

  maven_jar(
      name = "netty-transport-native-unix-common",
      artifact = "io.netty:netty-transport-native-unix-common:4.1.124.Final",
  )

  maven_jar(
      name = "netty-codec",
      artifact = "io.netty:netty-codec:4.1.124.Final",
  )

  maven_jar(
      name = "netty-resolver-dns",
      artifact = "io.netty:netty-resolver-dns:4.1.124.Final",
  )

  maven_jar(
      name = "netty-codec-dns",
      artifact = "io.netty:netty-codec-dns:4.1.124.Final",
  )

  maven_jar(
      name = "netty-transport-native-epoll",
      artifact = "io.netty:netty-transport-native-epoll:4.1.124.Final",
  )

  maven_jar(
      name = "netty-transport-classes-epoll",
      artifact = "io.netty:netty-transport-classes-epoll:4.1.124.Final",
  )

  maven_jar(
      name = "mutiny",
      artifact = "io.smallrye.reactive:mutiny:2.8.0",
  )

  maven_jar(
      name = "smallrye-common-annotation",
      artifact = "io.smallrye.common:smallrye-common-annotation:2.8.0",
  )

  maven_jar(
      name = "jctools-core",
      artifact = "org.jctools:jctools-core:4.0.5",
  )

  maven_jar(
      name = "jakarta-enterprise-cdi-api",
      artifact = "jakarta.enterprise:jakarta.enterprise.cdi-api:3.0.1",
  )

  maven_jar(
      name = "jakarta-el-api",
      artifact = "jakarta.el:jakarta.el-api:4.0.0",
  )

  maven_jar(
      name = "jakarta-inject-api",
      artifact = "jakarta.inject:jakarta.inject-api:2.0.1",
  )

  maven_jar(
      name = "jakarta-interceptor-api",
      artifact = "jakarta.interceptor:jakarta.interceptor-api:2.0.1",
  )

  maven_jar(
      name = "jakarta-annotation-api",
      artifact = "jakarta.annotation:jakarta.annotation-api:2.0.0",
  )

  maven_jar(
      name = "infinispan-api",
      artifact = "org.infinispan:infinispan-api:15.2.6.Final",
  )

  maven_jar(
      name = "wildfly-elytron-sasl-digest",
      artifact = "org.wildfly.security:wildfly-elytron-sasl-digest:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-auth-server",
      artifact = "org.wildfly.security:wildfly-elytron-auth-server:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-auth",
      artifact = "org.wildfly.security:wildfly-elytron-auth:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-asn1",
      artifact = "org.wildfly.security:wildfly-elytron-asn1:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-base",
      artifact = "org.wildfly.security:wildfly-elytron-base:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-credential",
      artifact = "org.wildfly.security:wildfly-elytron-credential:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-keystore",
      artifact = "org.wildfly.security:wildfly-elytron-keystore:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-util",
      artifact = "org.wildfly.security:wildfly-elytron-util:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-provider-util",
      artifact = "org.wildfly.security:wildfly-elytron-provider-util:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-x500-cert",
      artifact = "org.wildfly.security:wildfly-elytron-x500-cert:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-x500",
      artifact = "org.wildfly.security:wildfly-elytron-x500:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-x500-cert-util",
      artifact = "org.wildfly.security:wildfly-elytron-x500-cert-util:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-permission",
      artifact = "org.wildfly.security:wildfly-elytron-permission:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-mechanism",
      artifact = "org.wildfly.security:wildfly-elytron-mechanism:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-http",
      artifact = "org.wildfly.security:wildfly-elytron-http:2.6.2.Final",
  )

  maven_jar(
      name = "jakarta-servlet-api",
      artifact = "jakarta.servlet:jakarta.servlet-api:5.0.0",
  )

  maven_jar(
      name = "wildfly-elytron-mechanism-digest",
      artifact = "org.wildfly.security:wildfly-elytron-mechanism-digest:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-sasl",
      artifact = "org.wildfly.security:wildfly-elytron-sasl:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-ssl",
      artifact = "org.wildfly.security:wildfly-elytron-ssl:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-sasl-external",
      artifact = "org.wildfly.security:wildfly-elytron-sasl-external:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-sasl-gs2",
      artifact = "org.wildfly.security:wildfly-elytron-sasl-gs2:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-mechanism-gssapi",
      artifact = "org.wildfly.security:wildfly-elytron-mechanism-gssapi:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-security-manager-action",
      artifact = "org.wildfly.security:wildfly-elytron-security-manager-action:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-sasl-gssapi",
      artifact = "org.wildfly.security:wildfly-elytron-sasl-gssapi:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-sasl-oauth2",
      artifact = "org.wildfly.security:wildfly-elytron-sasl-oauth2:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-mechanism-oauth2",
      artifact = "org.wildfly.security:wildfly-elytron-mechanism-oauth2:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-sasl-plain",
      artifact = "org.wildfly.security:wildfly-elytron-sasl-plain:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-sasl-scram",
      artifact = "org.wildfly.security:wildfly-elytron-sasl-scram:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-mechanism-scram",
      artifact = "org.wildfly.security:wildfly-elytron-mechanism-scram:2.6.2.Final",
  )

  maven_jar(
      name = "wildfly-elytron-password-impl",
      artifact = "org.wildfly.security:wildfly-elytron-password-impl:2.6.2.Final",
  )
