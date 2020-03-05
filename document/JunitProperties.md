## JUnit properties

JUnit provide properties.

Just make `junit-platform.properties` file in test folder.

There are few setting at JUnit properties

- Test instance life cycle setting
~~~
junit.jupiter.testinstance.lifecycle.default = per_class
~~~

- Detect extension automatically
~~~
junit.jupiter.extensions.autodetection.enabled = true
~~~

- Execute ignore `@Disabled`
~~~
junit.jupiter.conditions.deactivate = org.junit.*DisabledCondition
~~~

- Setting mark test name
~~~
junit.jupiter.displayname.generator.default = \
    org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores
~~~