$(document).ready(function() {
  function randomStyle() {
    var img = "/img/" + Math.floor((Math.random()*17)+1) + '.jpg';
    return {backgroundOpacity: 0.4,
            background: "url(" + img + ") no-repeat center center fixed",
            backgroundSize: 'cover'}
  }

  $('#generate').on('click', function(e) {
    e.preventDefault;
    $.getJSON('/startup', function(data) {
      $('#company').html("<h1>" +
                         data.cname +
                         "</h1>" +
                         "<h3>" +
                         data.catchphrase +
                         "</h3>"
                         )
    });
    $('body').css(randomStyle());
    return false;
  });
});
