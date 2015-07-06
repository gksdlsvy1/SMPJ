<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>구매자 회원가입</title>
</head>
<body>
	<h2>구매자 회원 정보 입력</h2>
	<form action="customerSignUpStep3" method="post">
	<p>
		<label>이메일:<br>
		<input type="text" name="email" id="email" value="${registerRequest.email}">
		</label>
	</p>
	<p>
		<label>이름:<br>
		<input type="text" name="name" id="name" value="${registerRequest.name}">
		</label>
	</p>
	<p>
		<label>비밀번호:<br>
		<input type="password" name="password" id="password">
		</label>
	</p>
	<p>
		<label>비밀번호 확인:<br>
		<input type="password" name="confirmPassword" id="confirmPassword">
		</label>
	</p>
	<p>
		<label>전화번호:<br>
		<input type="text" name="phone" id="phone" >
		</label>
	</p>
	<p>
		<label>계좌번호:<br>
		<input type="text" name="account_num" id="account_num" >
		</label>
	</p>
	<p>
		<label>계좌 사용자 이름:<br>
		<input type="text" name="account_name" id="account_name" >
		</label>
	</p>
	<input type="submit" value="가입 완료">
	</form>
	
	<%--
	<form:form action="step3" commandName="registerRequest">
	<p>
		<label>이메일:<br>
		<form:input path="email" />
		</label>
	</p>
	<p>
		<label>이름:<br>
		<form:input path="name" />
		</label>
	</p>
	<p>
		<label>비밀번호:<br>
		<form:password path="password" />
		</label>
	</p>
	<p>
		<label>비밀번호 확인:<br>
		<form:password path="confirmPassword" />
		</label>
	</p>
	<input type="submit" value="가입 완료">
	</form:form>
	--%>
</body>
</html>