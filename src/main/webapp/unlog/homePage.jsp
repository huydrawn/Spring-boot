<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet" />
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet" />
<!-- MDB -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css"
	rel="stylesheet" />
<style type="text/css">
body {
	display: flex;
	justify-content: center;
	align-content: center;
	margin-top: 100px;
}
</style>
</head>
<body>
	<div style="border-radius: 20px; border: 1px solid; padding: 20px;">
		<form:form method="Get"
			>
			<!-- Email input -->
			<div class="form-outline mb-4">
				<input type="email" id="form2Example1" class="form-control" /> <label
					class="form-label" for="form2Example1">Email address</label>
			</div>

			<!-- Password input -->
			<div class="form-outline mb-4">
				<input type="password" id="form2Example2" class="form-control" /> <label
					class="form-label" for="form2Example2">Password</label>
			</div>

			<!-- 2 column grid layout for inline styling -->
			<div class="row mb-4">
				<div class="col d-flex justify-content-center">
					<!-- Checkbox -->
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value=""
							id="form2Example31" checked /> <label class="form-check-label"
							for="form2Example31"> Remember me </label>
					</div>
				</div>

				<div class="col">
					<!-- Simple link -->
					<a href="#!">Forgot password?</a>
				</div>
			</div>

			<!-- Submit button -->
			<button type="button" class="btn btn-primary btn-block mb-4">Sign
				in</button>

			<!-- Register buttons -->

		</form:form>
		<div class="text-center">
			<p> sign up with:</p>
			<div style="display: inline-flex;">
			<button type="button" class="btn btn-link btn-floating mx-1">
				<i class="fab fa-facebook-f"></i>
			</button>

			<form action="/loginByG">
				<button type="submit" class="btn btn-link btn-floating mx-1">
					<i class="fab fa-google"></i>
				</button>
			</form>

			<button type="submit" class="btn btn-link btn-floating mx-1">
				<i class="fab fa-twitter"></i>
			</button>


			<button type="button" class="btn btn-link btn-floating mx-1">
				<i class="fab fa-github"></i>
			</button>
			</div>
		</div>
	</div>
</body>
</html>