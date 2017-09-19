package pl.jp.s3uploader

import org.springframework.stereotype.Component
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("/")
@Produces("application/json")
@Component
class IndexController {

    @GET
    fun getName(): ApplicationName {
        return ApplicationName("s3-uploader")
    }
}

data class ApplicationName(val applicationName: String)
