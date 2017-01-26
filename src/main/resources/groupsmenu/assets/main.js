$(document).ready(function () {
  var $groupBtn = $('ul.navbar-nav a:contains("Groups")');
  var $li = $groupBtn.closest('li');
  $.getJSON(basePath + '/mygroups', null)
    .error(function () {
      $li.remove();
    })
    .success(function (json) {
      var $ul = $('<ul class="dropdown-menu">');
      for (var k in json) {
        var name = json[k];
        $ul.append($('<li>').append($('<a href="' + name + '">' + name + '</a>')));
      }
      $li.addClass('dropdown');
      $groupBtn.append('<span class="caret"></span>')
        .addClass('dropdown-toggle').attr('data-toggle', 'dropdown')
        .after($ul);
    });
});