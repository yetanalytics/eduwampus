$(document).ready(function() {
  function randomStyle() {
    var img = "/img/" + Math.floor((Math.random()*17)+1) + '.jpg';
    return {backgroundOpacity: 0.4,
            background: "url(" + img + ") no-repeat center center fixed",
            backgroundSize: 'cover'}
  }


  var renderStartup = function(seed) {
    $.getJSON('/startup/' + seed, function(data) {
      $('#company').html("<h1>" +
                         data.cname +
                         "</h1>" +
                         "<h3>" +
                         data.catchphrase +
                         "</h3>"
                         )
      $('body').css(data.styles);
      angelSearch(data.cname);
    });
  }

  function angelSearch(name) {
    $.ajax({
      dataType: 'json',
      url: '/angel-search',
      data: {startup: name},
      success: function(data) {
        $('#angel').html("<h4>" +
                         "Whoa! Looks like " +
                         data.name +
                         " actually exists! " +
                         "<a href='" +
                         data.angellist_url +
                         "' target='_blank'>" +
                         "View it on AngelList!" +
                         "</h4>");
      },
      error: function(jqXHR, status, error) {
        return false;
      }
    });
  }

  if (window.location.hash != "") {
    renderStartup(window.location.hash.substring(1));
  }

  $('#generate').on('click', function(e) {
    e.preventDefault;
    $('#angel').html('')
    var newSeed = Math.floor(Math.random()*30000);
    renderStartup(newSeed);
    window.location.hash = newSeed;
    return false
  });
});
