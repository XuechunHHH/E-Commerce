import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import swal from "sweetalert";

window.axios = require("axios");
window.swal = swal;

createApp(App).use(router).mount("#app");
