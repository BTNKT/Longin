$(document).ready(function() {
	$('#loginForm').on('submit', function(event) {
		event.preventDefault(); 

		$.ajax({
			url: '/demo/login', 
			type: 'POST',
			data: $(this).serialize(),
			dataType: 'json',
			success: function(response) {
				if (response.success) {
					window.location.href = '/demo/welcome'; 
				} else {
					$('#error-message').text(response.message); 
				}
			},
			error: function(xhr, status, error) {
				$('#error-message').text('帳號密碼錯誤請再輸入一次');
			}
		});
	});
});