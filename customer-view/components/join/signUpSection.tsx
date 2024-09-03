import React, {ChangeEvent} from 'react';
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import {FieldValues, SubmitHandler, useForm, Controller} from "react-hook-form";
import MemberApi from "@/shared/api/MemberApi";
import Validation from "@/shared/utils/validation/Validation";
import {useMutation} from "@tanstack/react-query";
function SignUpSection(props) {
    const {register, handleSubmit, formState: {errors},watch} = useForm<SignUpFormData>();
    const onSubmit = (memberData: SignUpFormData) => {
        signUpMutation.mutate(memberData);
    }
    const signUpMutation = useMutation(MemberApi.signUp, {
        onMutate: variable => {
            console.log("onMutate", variable);
            // variable : {loginId: 'xxx', password; 'xxx'}
        },
        onError: (error, variable, context) => {
            // error
        },
        onSuccess: (data, variables, context) => {
            console.log("success", data, variables, context);
        },
        onSettled: () => {
            console.log("end");
        }
    });

    const validation= Validation;
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
                    ...validation.rules
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
                            ...validation.email
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
                            ...validation.password
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
                    <Link href="/signIn" variant="body2">
                        Already have an account? Sign in
                    </Link>
                </Grid>
            </Grid>
        </Box>
    );
}

export default SignUpSection;