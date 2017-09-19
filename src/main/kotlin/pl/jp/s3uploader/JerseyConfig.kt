package pl.jp.s3uploader

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
class JerseyConfig : ResourceConfig {
    constructor() {
        register(IndexController::class.java)
    }
}
