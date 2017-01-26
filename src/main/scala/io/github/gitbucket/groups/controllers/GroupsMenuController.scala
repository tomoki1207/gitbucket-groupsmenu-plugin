package io.github.gitbucket.groups.controllers

import gitbucket.core.api.JsonFormat
import gitbucket.core.controller.ControllerBase
import gitbucket.core.service.AccountService
import gitbucket.core.util.UsersAuthenticator
import gitbucket.core.util.Implicits._

/**
  * Created by t_maruyama on 2017/01/26.
  */
class GroupsMenuController extends GroupsMenuControllerBase with AccountService with UsersAuthenticator

trait GroupsMenuControllerBase extends ControllerBase {
  self: AccountService with UsersAuthenticator =>

  get("/mygroups") {
    contentType = "application/json"
    JsonFormat(getGroupsByUserName(context.loginAccount.get.userName))
  }
}
