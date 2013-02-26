dataSource {
    pooled = true
    dbCreate = "update"
    driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
    username = "ubTSe64sRgALl"
    password = "pJwTzQyDEtrEv"}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost/DealSensorDev2?useUnicode=yes&characterEncoding=UTF-8"
			//url = "jdbc:mysql://localhost:10000/d93a59ac3fd40431fa44ff8b27f092d1c?useUnicode=yes&characterEncoding=UTF-8"
            //username = "ubTSe64sRgALl"
            //password = "pJwTzQyDEtrEv"
			username = "root"
			password = "admin"

        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/DealSensorDev2?useUnicode=yes&characterEncoding=UTF-8"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/DealSensorDev2?useUnicode=yes&characterEncoding=UTF-8"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
