$(document).ready(function() {
  function randomStyle() {
    var img = "/img/" + Math.floor((Math.random()*17)+1) + '.jpg';
    return {backgroundOpacity: 0.4,
            background: "url(" + img + ") no-repeat center center fixed",
            backgroundSize: 'cover'}
  }

  var startupName;

  var renderStartup = function(seed) {
    $.getJSON('/startup/' + seed, function(data) {
      startupName = data.cname
      $('title').text(data.cname + ' - eduwampus');
      $('#company').html("<h1>" +
                         data.cname +
                         "</h1>" +
                         "<h3>" +
                         data.catchphrase +
                         "</h3>"
                         )
      $('body').css(data.styles);
      $('#twitter-area').html('<a href="https://twitter.com/share" class="twitter-share-button" id="tweet" data-lang="en" data-url="" data-text="" data-hashtags="edtech">Tweet</a>');
      $('a#tweet').attr('data-url', ("http://eduwampus.herokuapp.com/" + window.location.hash ))
               .attr('data-text', document.title + ", the EdTech Startup Generator");
      //!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="https://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
      $.ajax({ url: 'http://platform.twitter.com/widgets.js', dataType: 'script', cache:true});
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

  $(window).on('hashchange', function() {
    renderStartup(window.location.hash.substring(1));

  });

  $('#generate').on('click', function(e) {
    e.preventDefault;
    $('#angel').html('')
    var newSeed = Math.floor(Math.random()*30000);
    renderStartup(newSeed);
    window.location.hash = newSeed;
    return false
  });


});
