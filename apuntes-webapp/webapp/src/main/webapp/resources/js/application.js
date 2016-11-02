$(document).ready(function ($, html) {

    $(".course-select").select2({
        ajax: {
            url: "api/course/search",
            processResults: function (data) {
                return {
                    results: data
                };
            }
        }
    });

    $(".course-on-nav").bind('change', function () {
       var url = $(this).text().split(' ')[0]; // get selected value
       if (url) {
          window.location = "course/"+url;
       }
       return false;
     });

    $(".course-inprogram-select").select2();

    function checkVisible(index, value) {
    	if($(value).find('li').size() === $(value).find('.hidden').size()){
    		$(value).hide();
    	} else {
    		$(value).show()
    	}
    }

    var courses = $('.course-item');
    $('#filter').keyup(function() {
        var re = new RegExp($(this).val(), "i"); // "i" means it's case-insensitive
        courses.removeClass('hidden').show().filter(function() {
            return !re.test($(this).text());
        }).addClass('hidden').hide();
        var semesters = $('.semester-wrapper');
        $.each(semesters, checkVisible);
    });

    $("a.back-button").click(function(){
        parent.history.back();
        return false;
    });

    $('.scroll-indicator-item').click( function() {
        var page = $(this).attr('href');
        var speed = 1200;
        $("#navbar").removeClass("in")
        $('html, body').animate( { scrollTop: $(page).offset().top - 50}, speed );
        return false;
    });

    //#to-top button appears after scrolling
    var fixed = false;
    $(document).scroll(function() {
        if ($(this).scrollTop() > 250) {
            if (!fixed) {
                fixed = true;
                // $('#to-top').css({position:'fixed', display:'block'});
                $('#to-top').show("slow", function() {
                    $('#to-top').css({
                        position: 'fixed',
                        display: 'block'
                    });
                });
            }
        } else {
            if (fixed) {
                fixed = false;
                $('#to-top').hide("slow", function() {
                    $('#to-top').css({
                        display: 'none'
                    });
                });
            }
        }
    });

    // Moment
    moment.locale("es");
    $('.moment-ago').each(function(i, e) {
        var time = moment($(e).attr('datetime'));
        $(e).html(time.fromNow());
    });

});
