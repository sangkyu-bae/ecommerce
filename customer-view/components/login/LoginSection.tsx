import React, {ChangeEvent} from 'react';
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import {FieldValues, SubmitHandler, useForm, Controller} from "react-hook-form";
import {useController} from "react-hook-form";
import {FormControlLabelProps, Input, RadioGroupProps} from "@mui/material";

type SignupFormData = {
    name: string;
    email: string;
    password: string;
    confirmPassword: string;
};

function LoginSection(props) {
    const {register, handleSubmit, formState: {errors}, control,watch} = useForm<SignupFormData>();
    const onSubmit = (data: SignupFormData) => {
        console.log(data);
        console.log(errors)
        alert(JSON.stringify(data));
    }
    const rules = {
        required: "이름을 입력해주세요.",
        maxLength: {
            value: 10,
            message: "이름은 최대 10자까지 입력 가능합니다.",
        },
        minLength: {
            value: 2,
            message: "이름은 최소 2자 이상 입력해야 합니다.",
        },
    };
    return (
        <Box component="form" noValidate onSubmit={handleSubmit(onSubmit)} sx={{mt: 3}}>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={12}>
                <TextField
                    autoComplete="given-name"
                    name="name"
                    required
                    fullWidth
                    id="name"
                    label="이름"
                    autoFocus
                    {...register("name", {
                    ...rules
                    })}
                    error={Boolean(errors.name)}
                    helperText={errors.name?.message}
                />

                </Grid>
                <Grid item xs={12}>
                    <TextField
                        required
                        fullWidth
                        id="email"
                        label="이메일 주소"
                        name="email"
                        autoComplete="email"
                        {...register("email", {
                            required:true,
                            pattern: {
                                value:
                                    /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,
                                message: "이메일 형식에 맞지 않습니다.",
                            },
                        })}
                        error={Boolean(errors.email)}
                        helperText={errors.email?.message}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        required
                        fullWidth
                        name="password"
                        label="비밀번호 (숫자+영문자+특수문자 8자리 이상)"
                        type="password"
                        id="password"
                        autoComplete="new-password"
                        {...register("password", {
                            required: true,
                            minLength: 8,
                            maxLength: 20,
                            pattern: {
                                value: /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/,
                                message: '비밀번호는 영문자, 특수문자, 숫자를 모두 포함하여 8자 이상 입력하세요.',
                            },
                        })}
                        error={Boolean(errors.password)}
                        helperText={errors.password?.message}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        required
                        fullWidth
                        name="check_password"
                        label="비밀번호 확인"
                        type="password"
                        id="check_password"
                        autoComplete="new-password"
                        {...register("confirmPassword", {
                            required: true,
                            validate: (value) =>
                                value === watch("password") || "비밀번호가 일치하지 않습니다.",
                        })}
                        error={Boolean(errors.confirmPassword)}
                        helperText={errors.confirmPassword?.message}
                    />
                </Grid>
            </Grid>
            <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{mt: 3, mb: 2}}
            >
                회원가입
            </Button>
            <Grid container justifyContent="flex-end">
                <Grid item>
                    <Link href="#" variant="body2">
                        Already have an account? Sign in
                    </Link>
                </Grid>
            </Grid>
        </Box>
    );
}

export default LoginSection;