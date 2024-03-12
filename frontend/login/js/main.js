
(function ($) {
    "use strict";


     /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }

      /*========================== AJAX REGISTER USER ==========================*/
      $('#loginForm').on('submit', function(event) {
        var check = true;

        var hasDonated = $('input[name="jaDoou"]:checked').val() === 'yes';
    
        input.each(function(){
            if (!hasDonated && $(this).attr('name') === 'lastdonationdate') {
                return true;
            }
            if(validate(this) == false){
                showValidate(this);
                check = false;
            }
        });

        if(!check) {
            console.log("Form check didn't pass")
            event.preventDefault();
            return;
        }
        
        event.preventDefault();

        var loginData = {
            username: $('input[name="username"]').val(),
            password: $('input[name="pass"]').val(),
        };

        //Register user
        $.ajax({
            url: 'http://localhost:8081/api/user/login',
            type: 'POST',
            data: JSON.stringify(loginData),
            contentType: "application/json",
            success: function(response, status, xhr) {
                console.log("User Logged in");
                var tokenJWT = response.tokenJWT;
                console.log("Token JWT", tokenJWT);
                sessionStorage.setItem('jwtToken', tokenJWT);
                window.location.href = 'http://localhost:8080/doador/';
            },
            error: function(error) {
                console.error("Registration failed", error);
            }
        });
    });
})(jQuery);