import { styled, createTheme } from "@mui/material/styles";
import { AppBar, Toolbar, Typography, Box, IconButton } from "@mui/material";
import Link from "next/link";

export const theme = createTheme({
    palette: {
        primary: {
            main: '#2c3e50',
        },
    },
});

export const StyledAppBar = styled(AppBar)(({ theme }) => ({
    boxShadow: 'none',
    borderBottom: '1px solid rgba(255, 255, 255, 0.1)',
}));

export const StyledToolbar = styled(Toolbar)({
    minHeight: '64px',
    display: 'flex',
    justifyContent: 'space-between',
});

export const LogoText = styled(Typography)({
    fontWeight: 700,
    letterSpacing: '0.5px',
});

export const NavLink = styled(Link)({
    textDecoration: 'none',
    color: 'inherit',
    marginLeft: '32px',
    transition: 'opacity 0.2s',
    '&:hover': {
        opacity: 0.8,
    },
});

export const NavText = styled(Typography)({
    fontSize: '0.95rem',
    fontWeight: 500,
});

export const IconsContainer = styled(Box)({
    display: 'flex',
    alignItems: 'center',
    gap: '20px',
});

export const StyledIconButton = styled(IconButton)({
    color: 'white',
    '&:hover': {
        background: 'rgba(255, 255, 255, 0.1)',
    },
}); 