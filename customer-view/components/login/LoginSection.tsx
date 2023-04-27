import React from 'react';
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import {useForm} from "react-hook-form";
type SignupFormData = {
    name: string;
    email: string;
    password: string;
    confirmPassword: string;
};

function LoginSection(props) {

    const { register, handleSubmit, formState: { errors } } = useForm<SignupFormData>();
    const onSubmit = handleSubmit((data) => {
        console.log(data);
    });
    return (
        <Box component="form" noValidate onSubmit={onSubmit} sx={{ mt: 3 }}>
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

                    />
                </Grid>
            </Grid>
            <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
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