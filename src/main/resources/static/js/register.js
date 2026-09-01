document.addEventListener("DOMContentLoaded", function () {

    const form = document.querySelector(".register-form");

    form.addEventListener("submit", async function (event) {

        event.preventDefault();

        const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const mobile = document.getElementById("mobile").value.trim();
        const dob = document.getElementById("dob").value;
        const address = document.getElementById("address").value.trim();
        const pincode = document.getElementById("pincode").value.trim();
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirm-password").value;

        // Check password
        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        const user = {
            name: name,
            email: email,
            mobile: mobile,
            dateOfBirth: dob,
            address: address,
            pincode: pincode,
            password: password
        };

        try {

            const response = await fetch("/api/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(user)
            });

            const result = await response.text();

            if (result === "Registration successful") {

                alert("Account created successfully!");

                window.location.href = "login.html";

            } else {

                alert(result);
            }

        } catch (error) {

            console.error(error);
            alert("Unable to connect to server.");
        }
    });
});