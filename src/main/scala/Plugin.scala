import javax.servlet.ServletContext

import gitbucket.core.controller.Context
import gitbucket.core.plugin.{Link, PluginRegistry}
import gitbucket.core.service.SystemSettingsService.SystemSettings
import io.github.gitbucket.solidbase.model.Version
import io.github.gitbucket.groups.controllers.GroupsMenuController

/**
  * Created by t_maruyama on 2017/01/26.
  */
class Plugin extends gitbucket.core.plugin.Plugin {
  override val pluginId: String = "groupsmenu"
  override val pluginName: String = "Groups in global menu Plugin"
  override val description: String = "Show groups link in global menu bar"
  override val versions: List[Version] = List(new Version("1.0.0"))

  override val controllers = Seq(
    "/*" -> new GroupsMenuController()
  )

  override val assetsMappings = Seq("/groupsmenu" -> "groupsmenu/assets")

  override val globalMenus = Seq(
    (context: Context) => Some(Link("groups", "Groups", "#"))
  )

  override def javaScripts(registry: PluginRegistry, context: ServletContext, settings: SystemSettings): Seq[(String, String)] = {
    val path = settings.baseUrl.getOrElse(context.getContextPath)
    Seq(
      ".*" -> s"""
       |</script>
       |<script>var basePath = "$path";</script>
       |<script src="$path/plugin-assets/groupsmenu/main.js"></script>
       |<script>
       """.stripMargin
    )
  }
}