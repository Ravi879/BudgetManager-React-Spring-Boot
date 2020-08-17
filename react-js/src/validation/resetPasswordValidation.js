import * as yup from "yup";

export const validationSchema = yup.object({
    password: yup.string().trim()
        .min(5, "Password must have at least 5 character")
        .max(14, "Password must be less than 14 character")
        .matches(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!#?&])[A-Za-z\d@$!%*#?&]{5,14}$/, "Password must contain one letter, one digit and one special character(@$!#?&)")
        .required("Please enter password"),
    password2: yup.string().trim()
        .oneOf([yup.ref('password'), null], 'Password does not match')
        .required("Confirm your password")
});

export const initialValues = {
    password: '',
    password2: ''
}
