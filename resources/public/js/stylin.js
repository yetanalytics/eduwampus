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
    });
  }

  if (window.location.hash != "") {
    renderStartup(window.location.hash.substring(1));
  }

  $('#generate').on('click', function(e) {
    e.preventDefault;
    var newSeed = Math.floor(Math.random()*30000);
    renderStartup(newSeed);
    window.location.hash = newSeed;
    return false
  });
});
