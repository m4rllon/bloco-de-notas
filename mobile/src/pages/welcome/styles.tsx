import styled from "styled-components/native";

export const HeaderContainer = styled.View`
    align-self: flex-start;

    padding: ${ props => props.theme.spacing.md }px;

    background-color: ${ props => props.theme.colors.clay};

    border-radius: ${ props => props.theme.borderRadius.lg }px;
`

export const HeaderText = styled.Text`
    color: ${ props => props.theme.colors.paper };
    font-family: ${ props => props.theme.fonts.display.bold };
    font-size: 40px;
    line-height: 42px;

    text-align: center;
`

export const MainContainer = styled.View`
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: start;
    gap: ${ props => props.theme.spacing.md }px;
`

export const MainTextContainer = styled.View`
    width: auto;
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: start;
    gap: 0;
`

interface StyleProps{
    $primary?: boolean;
}

export const MainText = styled.Text<StyleProps>`
    color: ${ props => props.$primary ? props.theme.colors.clayDeep : props.theme.colors.ink };
    font-family: ${ props => props.theme.fonts.display.bold };
    font-size: 48px;
    line-height: 50px;
`

export const MainTextSecundary = styled.Text`
    color: ${ props => props.theme.colors.ink};
    font-size: 16px;
`

export const MenuContainer = styled.View`
    width: auto;
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: start;
    gap: ${ props => props.theme.spacing.md }px;
`
