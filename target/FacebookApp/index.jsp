<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="com.sample.oauth.core.FBConnection"%>
<%
	FBConnection fbConnection = new FBConnection();
	String gender = (String) "male";
%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://www.freeiconspng.com/uploads/contact-phone-icon-4.png">

    <title>Contact</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/gijgo@1.9.10/css/gijgo.min.css" rel="stylesheet" type="text/css">
	
  </head>

  <body class="bg-light">

    <div class="container">
      <div class="py-5 text-center">
      	<i class="fa fa-user-circle-o fa-5x"></i>
        <h2>Contact Form</h2>
        <p>Please fill in your information and we'll be sending your order in less time.</p>
		<hr>
      </div>

      <div class="row">
        <div class="col-md-4 order-md-2 mb-4">
         <h4 style="text-align: center;">Fill Contact Form using FB</h4>
         	<div style="margin-top:50px;">
         		<a href="<%=fbConnection.getFBAuthUrl()%>"><img class="img-fluid d-block mx-auto mb-4" src="https://i.stack.imgur.com/oL5c2.png" width="300" alt=""></a>
        	</div>
        </div>
        <div class="col-md-8 order-md-1">
          <h4 class="mb-3">Contact Information</h4>
          <form action="">
           	<div class="mb-3" style="margin-top:50px;">
           		<label for="profile-picture">Photo</label>
              	<img src="<%= request.getAttribute("id")==null ?"https://www.logolynx.com/images/logolynx/e0/e0c16f4356ff503bf0173e3fdaebbd31.png": "https://graph.facebook.com/"+request.getAttribute("id")+"/picture?width=500&height=500"%>" class="rounded img-thumbnail d-block" width="200" height="200" alt="...">
            </div>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="firstName">First name</label>
                <input type="text" class="form-control" id="firstName" placeholder="First name" value="<%= request.getAttribute("first_name")==null ?"": request.getAttribute("first_name")%>" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="lastName">Last name</label>
                <input type="text" class="form-control" id="lastName" placeholder="Last name" value="<%= request.getAttribute("last_name")==null ?"": request.getAttribute("last_name")%>" required>
              </div>
            </div>

            <div class="mb-3">
              <label for="email">Email</label>
              <input type="email" class="form-control" id="email" placeholder="Email" value="<%= request.getAttribute("email")==null ?"": request.getAttribute("email")%>" required>
            </div>

            <div class="mb-3">
              <label for="hometown">Home Town</label>
              <input type="text" class="form-control" id="hometown" placeholder="Home Town" value="<%= request.getAttribute("hometown")==null ?"": request.getAttribute("hometown")%>">
            </div>

            <div class="mb-3">
              <label for="current-location">Current location</label>
              <input type="text" class="form-control" id="current-location" placeholder="Current location" value="<%= request.getAttribute("location")==null ?"": request.getAttribute("location")%>" required>
            </div>
            
            <div class="row">
              <div class="col-md-6 mb-3">
              	<label for="dob">Date of birth</label>
              	<input id="dob" class="form-control" placeholder="Date of birth" value="<%= request.getAttribute("birthday")==null ?"": request.getAttribute("birthday")%>" required>
              </div>
              <div class="col-md-6 mb-3">
              	<label for="gender">Gender</label>
              	<div class="custom-control custom-radio">
              		<input type="radio" class="custom-control-input" name="gender" id="male" checked value="Male">
              		<label class="custom-control-label" for="male">Male</label>
            	</div>
            	<div class="custom-control custom-radio">
              		<input type="radio" class="custom-control-input" name="gender" id="female" value="Female">
              		<label class="custom-control-label" for="female">Female</label>
            	</div>
              </div>
            </div>
            <% 
            String gend = (String) request.getAttribute("gender");
            if(gend == null || "male".equals(gend)){%>
              	<script>
    				document.getElementById("male").checked = true;
				</script>
            <%}else {%>
              	<script>
    				document.getElementById("female").checked = true;
				</script>
			<%}%>
            <hr class="mb-4">
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" checked id="same-address">
              <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
            </div>
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" id="save-info">
              <label class="custom-control-label" for="save-info">Save this information for next time</label>
            </div>
            <hr class="mb-4">

            <h4 class="mb-3">Payment</h4>

            <div class="d-block my-3">
              <div class="custom-control custom-radio">
                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required>
                <label class="custom-control-label" for="credit">Credit card</label>
              </div>
              <div class="custom-control custom-radio">
                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required>
                <label class="custom-control-label" for="debit">Debit card</label>
              </div>
              <div class="custom-control custom-radio">
                <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                <label class="custom-control-label" for="paypal">Paypal</label>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="cc-name">Name on card</label>
                <input type="text" class="form-control" id="cc-name" placeholder="" required>
                <small class="text-muted">Full name as displayed on card</small>
              </div>
              <div class="col-md-6 mb-3">
                <label for="cc-number">Credit card number</label>
                <input type="text" class="form-control" id="cc-number" placeholder="" required>
              </div>
            </div>
            <div class="row">
              <div class="col-md-3 mb-3">
                <label for="cc-expiration">Expiration</label>
                <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
              </div>
              <div class="col-md-3 mb-3">
                <label for="cc-expiration">CVV</label>
                <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
              </div>
            </div>
            <hr class="mb-4">
            <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
          </form>
        </div>
      </div>

      <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2017-2018 Company Name</p>
        <ul class="list-inline">
          <li class="list-inline-item"><a href="#">Privacy</a></li>
          <li class="list-inline-item"><a href="#">Terms</a></li>
          <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
      </footer>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/gijgo@1.9.10/js/gijgo.min.js" type="text/javascript"></script>
    <script>
        $('#dob').datepicker({
            uiLibrary: 'bootstrap4'
        });
    </script>
  
  </body>
</html>

