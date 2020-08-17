import * as yup from "yup";

export const validationSchema = yup.object({
    firstName: yup.string().trim()
        .min(2, "Must have at least 2 character")
        .max(10, "Must be less than 10 character")
        .matches(/^[A-Za-z0-9]{2,10}$/, "Alphabets and digits only")
        .required("Please enter first name"),
    lastName: yup.string().trim()
        .min(2, "Must have at least 2 character")
        .max(10, "Must be less than 10 character")
        .matches(/^[A-Za-z0-9]{2,10}$/, "Alphabets and digits only")
        .required("Please enter last name"),
    email: yup.string().trim()
        .email("Enter valid email address")
        .max(100, "Password must be less than 100 character")
        .required("Please enter email address"),
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
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    password2: ''
}

/* export const initialValues = {
    firstName: 'Admin',
    lastName: 'Admin',
    email: 'gadhiyaravi879@gmail.com',
    password: 'aaA1##a',
    password2: 'aaA1##'
}
 */
