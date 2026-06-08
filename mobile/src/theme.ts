export const theme = {
    colors: {
        // Tinta / texto
        ink: '#2B2620',
        inkSoft: '#5A5147',
        muted: '#928879',
        // Papel / superfícies
        paper: '#F6EFE3',
        paper2: '#EFE6D5',
        paper3: '#E7DBC6',
        line: '#DCCFB8',
        // Acento (ação)
        clay: '#C25A3C',
        clayDeep: '#A4472C',
        // Sucesso / nuvem
        sage: '#6E7C5A',
        sageSoft: '#E4E7D6',
        // Estados
        danger: '#B23A33',
        dangerBorder: '#E3C4BF',
        gold: '#D89B3C',
        warn: '#9C6B2E',
        // Categorias / variações
        purple: '#8B6FB0',
        pillLocalBg: '#F0E2D0',
        warnBg: '#F4E7D5',
        // Fundo do mockup (gradiente)
        bgDark1: '#34302A',
        bgDark2: '#211D18',
        bgDark3: '#18140F',
        // Sombra
        shadow: 'rgba(43, 38, 32, 0.55)',
    },

    fonts: {
        display: {
            semibold: 'Fraunces_600SemiBold',
            bold: 'Fraunces_700Bold',
        },
        body: {
            regular: 'HankenGrotesk_400Regular',
            medium: 'HankenGrotesk_500Medium',
            semibold: 'HankenGrotesk_600SemiBold',
            bold: 'HankenGrotesk_700Bold',
        }
    },

    spacing: {
        xs:4,
        sm:8,
        md:16,
        lg:24,
        xl:32,
    },

    typography: {
        fontSizeSmall: 12,
        fontSizeMedium: 16,
        fontSizeLarge: 20,
        fontSizeXL: 24,
        fontWeightRegular: '400',
        fontWeightBold: '700',
    },

    borderRadius: {
        sm: 4,
        md: 8,
        lg: 16,
        full: 9999,
    }
}

export type Theme = typeof theme;