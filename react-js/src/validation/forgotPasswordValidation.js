import * as yup from "yup";

export const validationSchema = yup.object({
    email: yup.string().trim()
        .email("Enter valid email address")
        .required("Please enter your register email address"),
});

export const initialValues = {
    email: '',
}
