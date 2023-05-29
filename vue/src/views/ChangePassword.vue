<template>
  <div class="change-password">
    <h2>Change Password</h2>
    <form @submit.prevent="changePassword">
      <div class="form-group">
        <label for="currentPassword">Old password:</label>
        <input type="password" v-model="currentPassword" required>
      </div>
      <div class="form-group">
        <label for="newPassword">New password:</label>
        <div class="password-input">
          <input
              :type="showNewPassword ? 'text' : 'password'"
              v-model="newPassword"
              required
              @input="checkPasswordStrength"
          >
        </div>
        <div class="password-strength" :class="passwordStrengthClass">
          {{ passwordStrengthText }}
        </div>
      </div>
      <div class="form-group">
        <label for="confirmPassword">Confirm new password:</label>
        <div class="password-input">
          <input
              :type="showConfirmPassword ? 'text' : 'password'"
              v-model="confirmPassword"
              required
          >
        </div>
      </div>
      <button type="submit">Update password</button>
    </form>
  </div>
</template>

<script>
import api from '@/api/api';

export default {
  data() {
    return {
      currentPassword: '',
      newPassword: '',
      confirmPassword: '',
      showNewPassword: false,
      showConfirmPassword: false,
      passwordStrength: '',
      passwordStrengthText: ''
    };
  },
  methods: {
    changePassword() {
      if (this.newPassword !== this.confirmPassword) {
        this.$toast.error("New password and confirm password don't match.");
        return;
      }
      api.book.getOldPassword(this.$route.params.userid).then(res =>{
          const oldpwd = res.data;
          console.log(oldpwd);
          console.log(this.currentPassword);

          if (this.currentPassword !== oldpwd.toString()){
              this.$toast.error("Current password is incorrect.");
              return;
          }
        if (this.passwordStrength === 'weak'){
          this.$toast.error("New password strength is too weak!");
          return;
        }
          //原密码正确
          api.book.updatePassword(this.$route.params.userid, this.newPassword).then(()=>{
              console.log(this.currentPassword);
              this.currentPassword = '';
              this.newPassword = '';
              this.confirmPassword = '';
              this.$toast.success('Password changed successfully!');
          })
              .catch(error => {
                  console.error('Failed to update password:', error);
                  this.$toast.error('Failed to update password. Please try again.');
              });
      })
          .catch(error =>{
              console.error('Failed to get old password:', error);
              this.$toast.error('Failed to get old password. Please try again.');
          })
    },

    //密码强度提示
    checkPasswordStrength() {
      const strength = this.calculatePasswordStrength();
      switch (strength) {
        case 0:
          this.passwordStrength = 'weak';
          this.passwordStrengthText = 'Weak password';
          break;
        case 1:
          this.passwordStrength = 'medium';
          this.passwordStrengthText = 'Medium password';
          break;
        case 2:
          this.passwordStrength = 'strong';
          this.passwordStrengthText = 'Strong password';
          break;
        default:
          this.passwordStrength = '';
          this.passwordStrengthText = '';
          break;
      }
    },
    calculatePasswordStrength() {
      const password = this.newPassword;
      if (password.length < 6) {
        return 0; // Weak password
      }
      if (password.length < 10) {
        return 1; // Medium password
      }
      return 2; // Strong password
    },
  },
  computed: {
    passwordStrengthClass() {
      return {
        'password-strength-weak': this.passwordStrength === 'weak',
        'password-strength-medium': this.passwordStrength === 'medium',
        'password-strength-strong': this.passwordStrength === 'strong',
      };
    },
  },
};
</script>

<style scoped>
.change-password {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 8px;
  color: #555;
}

.password-input {
  position: relative;
}

input[type="password"],
input[type="text"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border-color 0.2s ease-in-out;
}

input[type="password"]:focus,
input[type="text"]:focus {
  border-color: #4CAF50;
}

.toggle-password {
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  cursor: pointer;
  color: #888;
}

.password-strength {
  margin-top: 6px;
  font-size: 12px;
}

.password-strength-weak {
  color: #ff4d4f;
}

.password-strength-medium {
  color: #ffad4f;
}

.password-strength-strong {
  color: #52c41a;
}

button {
  display: block;
  width: 100%;
  padding: 12px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
}

button:hover {
  background-color: #45a049;
}
</style>
