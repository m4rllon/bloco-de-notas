import styled from 'styled-components/native';
import { theme } from './src/theme';
import { ThemeProvider } from 'styled-components';
import { Text } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';

const Container = styled.View`
  height: 100%;
  background-color: ${ props => props.theme.colors.paper};
  padding: ${ props => props.theme.spacing.xl}px;

`

export default function App() {
  return (
    <SafeAreaView>
      <ThemeProvider theme={theme}>
          <Container>
            <Text>
              oi
              oi
              oi
              oi
              oi
            </Text>
          </Container>
      </ThemeProvider>
    </SafeAreaView>
  );
}
