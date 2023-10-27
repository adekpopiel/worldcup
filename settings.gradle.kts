rootProject.name = "worldcup"
include("domain")
include("usecase")
include("adapter:controller")
findProject(":adapter:controller")?.name = "controller"
include("adapter:repository:in-memory")
findProject(":adapter:repository:in-memory")?.name = "in-memory"
include("adapter:dateformatter:iso8601")
findProject(":adapter:dateformatter:iso8601")?.name = "iso8601"
