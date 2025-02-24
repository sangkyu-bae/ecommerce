import { NavLink, StyledIconButton } from './styles/HeaderStyles';
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';
import { Badge } from '@mui/material';
import { ReactNode } from 'react';

interface HeaderFactoryProps {
    isLogin: boolean;
    onLogout: () => void;
}

interface HeaderIconConfig {
    icon: ReactNode;
    href?: string;
    onClick?: () => void;
    label: string;
    badge?: number;
}

function HeaderFactory({ isLogin, onLogout }: HeaderFactoryProps) {
    const getIconConfigs = (): HeaderIconConfig[] => {
        if (!isLogin) {
            return [{
                icon: <LoginIcon />,
                href: '/signIn',
                label: '로그인'
            }];
        }

        return [
            {
                icon: <LogoutIcon />,
                onClick: onLogout,
                label: '로그아웃'
            },
            {
                icon: <ShoppingBasketIcon />,
                href: '/basket',
                label: '장바구니',
                badge: 0
            },
            {
                icon: <ManageAccountsIcon />,
                href: '/order/list',
                label: '계정관리'
            }
        ];
    };

    return (
        <>
            {getIconConfigs().map((config, index) => (
                config.href ? (
                    <NavLink key={index} href={config.href}>
                        <StyledIconButton aria-label={config.label}>
                            {config.badge !== undefined ? (
                                <Badge badgeContent={config.badge} color="error">
                                    {config.icon}
                                </Badge>
                            ) : config.icon}
                        </StyledIconButton>
                    </NavLink>
                ) : (
                    <StyledIconButton 
                        key={index}
                        onClick={config.onClick}
                        aria-label={config.label}
                    >
                        {config.icon}
                    </StyledIconButton>
                )
            ))}
        </>
    );
}

export default HeaderFactory;   

