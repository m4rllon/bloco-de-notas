import { HeaderContainer, HeaderText, MainContainer, MainText, MainTextContainer, MainTextSecundary, MenuContainer } from "./styles";
import { CustomButton } from "../../components/CustomButton";
import { Container } from "../../components/Container";

export function Welcome(){
    return <Container>
        <HeaderContainer>
            <HeaderText>BN</HeaderText>
        </HeaderContainer>
        <MainContainer>
            <MainTextContainer>
                <MainText>Pensou.</MainText>
                <MainText>Anotou.</MainText>
                <MainText $primary>Guardou.</MainText>
            </MainTextContainer>
            <MainTextSecundary>
                Comece a escrever agora — sem cadastro. Crie uma conta quando quiser guardar tudo na nuvem.
            </MainTextSecundary>
            <MenuContainer>
                <CustomButton type={"primary"} activeOpacity={0.7}>
                    Criar minha conta
                </CustomButton>
                <CustomButton type={"secundary"} activeOpacity={0.7}>
                    Já tenha conta
                </CustomButton>
                <CustomButton type={"aux"} activeOpacity={0.7}>
                    Continuar sem login
                </CustomButton>
            </MenuContainer>
        </MainContainer>
    </Container>
}