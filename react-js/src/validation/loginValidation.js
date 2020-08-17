import * as yup from "yup";

export const validationSchema = yup.object({
    email: yup.string().trim()
        .email("Enter valid email address")
        .max(100, "Password must be less than 100 character")
        .required("Please enter email address"),
    password: yup.string().trim()
        .required("Please enter password"),
    rememberMe: yup.boolean()
});

export const initialValues = {
    email: '',
    password: '',
    rememberMe: false
}
