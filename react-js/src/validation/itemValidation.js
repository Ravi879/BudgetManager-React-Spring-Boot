import * as yup from "yup";

export const validationSchema = yup.object({
    description: yup.string().trim()
        .min(2, "Description must be greater than 2 characters.")
        .max(100, "Description must be less than 100 characters.")
        .required("Please enter description"),

    amount: yup.number()
        .min(0, "Amount can not be negative.")
        .max(100000, "Amount can not greater than 1000000.")
        .required("Please enter amount"),

    date: yup.date().required("Please select date"),

    category: yup.string().trim().required("Please select category")
});